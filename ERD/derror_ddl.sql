DROP VIEW IF EXISTS v_prof;
DROP VIEW IF EXISTS actor_info;
DROP VIEW IF EXISTS customer_list;
DROP VIEW IF EXISTS film_list;
DROP VIEW IF EXISTS nicer_but_slower_film_list;
DROP VIEW IF EXISTS sales_by_film_category;
DROP VIEW IF EXISTS sales_by_store;
DROP VIEW IF EXISTS staff_list;
DROP VIEW IF EXISTS host_summary;
DROP VIEW IF EXISTS host_summary_by_file_io;
DROP VIEW IF EXISTS host_summary_by_file_io_type;
DROP VIEW IF EXISTS host_summary_by_stages;
DROP VIEW IF EXISTS host_summary_by_statement_latency;
DROP VIEW IF EXISTS host_summary_by_statement_type;
DROP VIEW IF EXISTS innodb_buffer_stats_by_schema;
DROP VIEW IF EXISTS innodb_buffer_stats_by_table;
DROP VIEW IF EXISTS innodb_lock_waits;
DROP VIEW IF EXISTS io_by_thread_by_latency;
DROP VIEW IF EXISTS io_global_by_file_by_bytes;
DROP VIEW IF EXISTS io_global_by_file_by_latency;
DROP VIEW IF EXISTS io_global_by_wait_by_bytes;
DROP VIEW IF EXISTS io_global_by_wait_by_latency;
DROP VIEW IF EXISTS latest_file_io;
DROP VIEW IF EXISTS memory_by_host_by_current_bytes;
DROP VIEW IF EXISTS memory_by_thread_by_current_bytes;
DROP VIEW IF EXISTS memory_by_user_by_current_bytes;
DROP VIEW IF EXISTS memory_global_by_current_bytes;
DROP VIEW IF EXISTS memory_global_total;
DROP VIEW IF EXISTS metrics;
DROP VIEW IF EXISTS processlist;
DROP VIEW IF EXISTS ps_check_lost_instrumentation;
DROP VIEW IF EXISTS schema_auto_increment_columns;
DROP VIEW IF EXISTS schema_index_statistics;
DROP VIEW IF EXISTS schema_object_overview;
DROP VIEW IF EXISTS schema_redundant_indexes;
DROP VIEW IF EXISTS schema_table_lock_waits;
DROP VIEW IF EXISTS schema_table_statistics;
DROP VIEW IF EXISTS schema_table_statistics_with_buffer;
DROP VIEW IF EXISTS schema_tables_with_full_table_scans;
DROP VIEW IF EXISTS schema_unused_indexes;
DROP VIEW IF EXISTS session;
DROP VIEW IF EXISTS session_ssl_status;
DROP VIEW IF EXISTS statement_analysis;
DROP VIEW IF EXISTS statements_with_errors_or_warnings;
DROP VIEW IF EXISTS statements_with_full_table_scans;
DROP VIEW IF EXISTS statements_with_runtimes_in_95th_percentile;
DROP VIEW IF EXISTS statements_with_sorting;
DROP VIEW IF EXISTS statements_with_temp_tables;
DROP VIEW IF EXISTS user_summary;
DROP VIEW IF EXISTS user_summary_by_file_io;
DROP VIEW IF EXISTS user_summary_by_file_io_type;
DROP VIEW IF EXISTS user_summary_by_stages;
DROP VIEW IF EXISTS user_summary_by_statement_latency;
DROP VIEW IF EXISTS user_summary_by_statement_type;
DROP VIEW IF EXISTS version;
DROP VIEW IF EXISTS wait_classes_global_by_avg_latency;
DROP VIEW IF EXISTS wait_classes_global_by_latency;
DROP VIEW IF EXISTS waits_by_host_by_latency;
DROP VIEW IF EXISTS waits_by_user_by_latency;
DROP VIEW IF EXISTS waits_global_by_latency;
DROP VIEW IF EXISTS x$host_summary;
DROP VIEW IF EXISTS x$host_summary_by_file_io;
DROP VIEW IF EXISTS x$host_summary_by_file_io_type;
DROP VIEW IF EXISTS x$host_summary_by_stages;
DROP VIEW IF EXISTS x$host_summary_by_statement_latency;
DROP VIEW IF EXISTS x$host_summary_by_statement_type;
DROP VIEW IF EXISTS x$innodb_buffer_stats_by_schema;
DROP VIEW IF EXISTS x$innodb_buffer_stats_by_table;
DROP VIEW IF EXISTS x$innodb_lock_waits;
DROP VIEW IF EXISTS x$io_by_thread_by_latency;
DROP VIEW IF EXISTS x$io_global_by_file_by_bytes;
DROP VIEW IF EXISTS x$io_global_by_file_by_latency;
DROP VIEW IF EXISTS x$io_global_by_wait_by_bytes;
DROP VIEW IF EXISTS x$io_global_by_wait_by_latency;
DROP VIEW IF EXISTS x$latest_file_io;
DROP VIEW IF EXISTS x$memory_by_host_by_current_bytes;
DROP VIEW IF EXISTS x$memory_by_thread_by_current_bytes;
DROP VIEW IF EXISTS x$memory_by_user_by_current_bytes;
DROP VIEW IF EXISTS x$memory_global_by_current_bytes;
DROP VIEW IF EXISTS x$memory_global_total;
DROP VIEW IF EXISTS x$processlist;
DROP VIEW IF EXISTS x$ps_digest_95th_percentile_by_avg_us;
DROP VIEW IF EXISTS x$ps_digest_avg_latency_distribution;
DROP VIEW IF EXISTS x$ps_schema_table_statistics_io;
DROP VIEW IF EXISTS x$schema_flattened_keys;
DROP VIEW IF EXISTS x$schema_index_statistics;
DROP VIEW IF EXISTS x$schema_table_lock_waits;
DROP VIEW IF EXISTS x$schema_table_statistics;
DROP VIEW IF EXISTS x$schema_table_statistics_with_buffer;
DROP VIEW IF EXISTS x$schema_tables_with_full_table_scans;
DROP VIEW IF EXISTS x$session;
DROP VIEW IF EXISTS x$statement_analysis;
DROP VIEW IF EXISTS x$statements_with_errors_or_warnings;
DROP VIEW IF EXISTS x$statements_with_full_table_scans;
DROP VIEW IF EXISTS x$statements_with_runtimes_in_95th_percentile;
DROP VIEW IF EXISTS x$statements_with_sorting;
DROP VIEW IF EXISTS x$statements_with_temp_tables;
DROP VIEW IF EXISTS x$user_summary;
DROP VIEW IF EXISTS x$user_summary_by_file_io;
DROP VIEW IF EXISTS x$user_summary_by_file_io_type;
DROP VIEW IF EXISTS x$user_summary_by_stages;
DROP VIEW IF EXISTS x$user_summary_by_statement_latency;
DROP VIEW IF EXISTS x$user_summary_by_statement_type;
DROP VIEW IF EXISTS x$wait_classes_global_by_avg_latency;
DROP VIEW IF EXISTS x$wait_classes_global_by_latency;
DROP VIEW IF EXISTS x$waits_by_host_by_latency;
DROP VIEW IF EXISTS x$waits_by_user_by_latency;
DROP VIEW IF EXISTS x$waits_global_by_latency;
SET SESSION FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS dept_temp;
DROP TABLE IF EXISTS exam_coupon;
DROP TABLE IF EXISTS t4_post;
DROP TABLE IF EXISTS t5_attachment;
DROP TABLE IF EXISTS t5_authority;
DROP TABLE IF EXISTS t5_comment;
DROP TABLE IF EXISTS t5_post;
DROP TABLE IF EXISTS t5_user;
DROP TABLE IF EXISTS t5_user_authorities;
DROP TABLE IF EXISTS t_credit;
DROP TABLE IF EXISTS t_customer;
DROP TABLE IF EXISTS t_department;
DROP TABLE IF EXISTS t_dept;
DROP TABLE IF EXISTS t_dept2;
DROP TABLE IF EXISTS t_emp;
DROP TABLE IF EXISTS t_emp2;
DROP TABLE IF EXISTS t_emp3;
DROP TABLE IF EXISTS t_exam01;
DROP TABLE IF EXISTS t_gift;
DROP TABLE IF EXISTS t_member;
DROP TABLE IF EXISTS t_post;
DROP TABLE IF EXISTS t_product;
DROP TABLE IF EXISTS t_professor;
DROP TABLE IF EXISTS t_sales;
DROP TABLE IF EXISTS t_student;
DROP TABLE IF EXISTS test_book;
DROP TABLE IF EXISTS test_department;
DROP TABLE IF EXISTS test_movie;
DROP TABLE IF EXISTS test_student;
DROP TABLE IF EXISTS attach;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS event_plan;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS openai_img;
DROP TABLE IF EXISTS openai_log;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS share_plan;
DROP TABLE IF EXISTS actor;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS film;
DROP TABLE IF EXISTS film_actor;
DROP TABLE IF EXISTS film_category;
DROP TABLE IF EXISTS film_text;
DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS language;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS rental;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS store;
DROP TABLE IF EXISTS sys_config;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS countrylanguage;

/* Create Tables */

CREATE TABLE attach
(
    id bigint NOT NULL AUTO_INCREMENT,
    post_id bigint NOT NULL,
    sourcename varchar(200) NOT NULL,
    filename varchar(200) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE authority
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);


CREATE TABLE comment
(
    id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    post_id bigint NOT NULL,
    comment_id bigint,
    content text NOT NULL,
    created_date datetime default now(),
    PRIMARY KEY (id)
);


CREATE TABLE event_plan
(
    id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    master_id varchar(50) NOT NULL,
    title varchar(150) NOT NULL,
    created_date datetime default now(),
    national varchar(50) NOT NULL,
    thumbnail varchar(200),
    PRIMARY KEY (id)
);


-- 이건 회원정보입니다.
CREATE TABLE member
(
    id bigint NOT NULL AUTO_INCREMENT,
    auth_id int NOT NULL,
    provider varchar(20),
    provider_id varchar(200),
    username varchar(100) NOT NULL,
    password varchar(200) NOT NULL,
    name varchar(20) NOT NULL,
    age int NOT NULL,
    phone varchar(16) NOT NULL,
    email varchar(80),
    thumbnail_img varchar(300) default 'default_thumbnail.png',
    created_date datetime default now(),
    PRIMARY KEY (id),
    UNIQUE (username)
);


CREATE TABLE openai_img
(
    id bigint NOT NULL AUTO_INCREMENT,
    openai_id bigint NOT NULL,
    filename varchar(500),
    PRIMARY KEY (id)
);


CREATE TABLE openai_log
(
    id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    question varchar(500),
    national varchar(50) NOT NULL,
    country_code varchar(20),
    city varchar(100),
    place varbinary(100),
    address varchar(100),
    longitude varchar(50),
    latitude varchar(50),
    detail text,
    language varchar(40),
    reg_date datetime default now(),
    PRIMARY KEY (id)
);


CREATE TABLE post
(
    id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    category varchar(30) NOT NULL,
    title varchar(80) NOT NULL,
    content longtext NOT NULL,
    created_date datetime NOT NULL default now(),
    view_cnt int default 0,
    thumbnail varchar(200) default 'default.jpg',
    PRIMARY KEY (id)
);


CREATE TABLE share_plan
(
    id bigint NOT NULL AUTO_INCREMENT,
    event_id bigint NOT NULL,
    invitee bigint NOT NULL,
    status char,
    created_date datetime default now(),
    PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE member
    ADD FOREIGN KEY (auth_id)
        REFERENCES authority (id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE comment
    ADD FOREIGN KEY (comment_id)
        REFERENCES comment (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE share_plan
    ADD FOREIGN KEY (event_id)
        REFERENCES event_plan (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE comment
    ADD FOREIGN KEY (member_id)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE event_plan
    ADD FOREIGN KEY (member_id)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;



ALTER TABLE openai_log
    ADD FOREIGN KEY (member_id)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE post
    ADD FOREIGN KEY (member_id)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE share_plan
    ADD FOREIGN KEY (invitee)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE openai_img
    ADD FOREIGN KEY (openai_id)
        REFERENCES openai_log (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE attach
    ADD FOREIGN KEY (post_id)
        REFERENCES post (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE comment
    ADD FOREIGN KEY (post_id)
        REFERENCES post (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


drop table if EXISTS calendar;
CREATE TABLE calendar(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         start_date DATE NOT NULL,
                         end_date DATE,
                         color VARCHAR(20),
                         all_day BOOLEAN DEFAULT TRUE,
                         memo TEXT,
                         member_id BIGINT NOT NULL ,
                         FOREIGN KEY (member_id) REFERENCES Member(id)
                     ON DELETE CASCADE
);

INSERT INTO authority (id,name) VALUES (1,'ROLE_USER'), (2,'ROLE_ADMIN');