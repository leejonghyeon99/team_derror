show tables;

CREATE TABLE tour
(
    id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    venue_id varchar(80) NOT NULL,
    segment varchar(50) NOT NULL,
    place_name varchar(150) NOT NULL,
    national_code varchar(15) NOT NULL,
    location varchar(50),
    address varchar(200),
    type varchar(50),
    city_name varchar(50),
    PRIMARY KEY (id)
);