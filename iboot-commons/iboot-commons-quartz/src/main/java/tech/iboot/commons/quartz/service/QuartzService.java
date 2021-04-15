package tech.iboot.commons.quartz.service;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luohong
 * @date 2021/1/4
 * @remark
 * @email luohong@iboot.tech
 * @url https://iboot.tech
 **/
@Service
public class QuartzService {
    private final Logger logger  = LoggerFactory.getLogger(getClass());
    /**
     * 注入任务调度器
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 创建任务
     * @param name 任务名称
     * @param group 任务所属分组
     * @param clazz 执行任务class
     * @param time 时间配置
     * @throws Exception
     */
    public void addJob(String name, String group, Class<? extends Job> clazz, String time) throws Exception {
        JobKey jobKey = new JobKey(name,group);

        if(checkExists(jobKey)){
            logger.info("Job name is {} group is {} is existed",name,group);
        }else{
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            //创建任务
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(name,group).build();
            //创建任务触发器
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
            //将触发器与任务绑定到调度器内
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }

    /**
     * 停止任务
     * @param name 任务名称
     * @param group 任务所属分组
     * @throws SchedulerException
     */
    public void pauseJob(String name,String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name,group);
        if(checkExists(jobKey)){
            scheduler.pauseJob(jobKey);
            logger.info("pause Job name is {} group is pauseJob {} success",name,group);
        }else{
            logger.info("Job name is {} group is {} doesn't exist",name,group);
        }
    }

    /**
     * 继续任务
     * @param name 任务名称
     * @param group 任务所属分组
     * @throws SchedulerException
     */
    public void resumeJob(String name,String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name,group);
        if(checkExists(jobKey)){
            scheduler.resumeJob(jobKey);
            logger.info("pause Job name is {} group is  resumeJob {} success",name,group);
        }else{
            logger.info("Job name is {} group is {} doesn't exist",name,group);
        }
    }

    /**
     * 修改任务时间
     * @param name 任务名称
     * @param group 任务所属分组
     * @param clazz 执行任务class
     * @param newTime 时间配置
     * @throws Exception
     */
    public void modifyJobTime(String name,String group,Class<? extends Job> clazz,String newTime) throws Exception {
        JobKey jobKey = new JobKey(name,group);
        deleteJob(jobKey);
        addJob(name,group,clazz,newTime);
    }

    /**
     * 删除任务
     * @param name 任务名称
     * @param group 任务所属分组
     * @throws SchedulerException
     */
    public void deleteJob(String name,String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name,group);
        if(checkExists(jobKey)){
            scheduler.deleteJob(jobKey);
            logger.info("pause Job name is {} group is  deleteJob {} success",name,group);
        }else{
            logger.info("Job name is {} group is {} doesn't exist",name,group);
        }
    }

    /**
     * 删除任务
     * @param jobKey 任务key
     * @throws SchedulerException
     */
    public void deleteJob(JobKey jobKey) throws SchedulerException {
        if(checkExists(jobKey)){
            scheduler.deleteJob(jobKey);
            logger.info("pause Job name is {} group is  deleteJob {} success",jobKey.getName(),jobKey.getGroup());
        }else{
            logger.info("Job name is {} group is {} doesn't exist",jobKey.getName(),jobKey.getGroup());
        }
    }

    /**
     * 任务是否存在
     * @param name 任务名称
     * @param group 任务所属分组
     * @return
     * @throws SchedulerException
     */
    public boolean checkExists(String name,String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name,group);
        return checkExists(jobKey);
    }

    /**
     * 任务是否存在
     * @param jobKey 任务key
     * @return
     * @throws SchedulerException
     */
    public boolean checkExists(JobKey jobKey) throws SchedulerException {
        return  scheduler.checkExists(jobKey);
    }
}
