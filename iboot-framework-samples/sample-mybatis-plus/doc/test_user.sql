-- MySql test_user table --

create table test_user
(
    id          varchar(64) not null primary key,
    name        varchar(32) null,
    account     varchar(32) null,
    create_time datetime    null,
    update_time datetime    null,
    deleted     tinyint     null
);

