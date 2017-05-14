DROP DATABASE demodb;
CREATE DATABASE IF NOT EXISTS demodb;
USE demodb;

CREATE TABLE employee (
	name		VARCHAR(20) ,
    id			VARCHAR(20) PRIMARY KEY,
	gender		ENUM('male', 'female') ,
	cellphone	VARCHAR(20) ,
    address		VARCHAR(20) ,
    age			SMALLINT ,
    created		TIMESTAMP DEFAULT 0,	
	updated		TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT employee VALUES("Chuck", "9487", "male", "0987487987", "Ilan", 35, NULL, NULL);
INSERT employee VALUES("Gary", "9488", "male", "0988408408", "Changhua", 26, NULL, NULL);
INSERT employee VALUES("Ada", "9489", "female", "0978478478", "Taipei", 20, NULL, NULL);
INSERT employee VALUES("Gary", "9490", "male", "0987889126", "Taipei", 23, NULL, NULL);

