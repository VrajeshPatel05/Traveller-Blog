CREATE TABLE post (
    post_id bigint not null AUTO_INCREMENT,
    user_name varchar(20) not null,
    post_time date,
    description varchar(120),
    image_path varchar(50),
    PRIMARY KEY (post_id)
)Auto_increment =1;