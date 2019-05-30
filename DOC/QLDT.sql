CREATE TABLE lecturers (
lecturer_id bigint(20) NOT NULL AUTO_INCREMENT,
first_name varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
last_name varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
full_name varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
gender varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
mobile varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
email varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
department_id bigint(20) NULL,
position_id bigint(20) NULL,
status int(1) NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (lecturer_id) ,
INDEX lecturer_idx (lecturer_id ASC, department_id ASC, position_id ASC) USING BTREE
)
COMMENT = 'Bảng giảng viên
';

CREATE TABLE officers (
officer_id bigint(20) NOT NULL AUTO_INCREMENT,
first_name varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
last_name varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
full_name varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
gender varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
mobile varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
email varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
department_id bigint(20) NULL,
position_id bigint(20) NULL,
status int(1) NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (officer_id) ,
INDEX officer_idx (officer_id ASC, department_id ASC, position_id ASC) USING BTREE
)
COMMENT = 'Bảng học viên, cán bộ công chức';

CREATE TABLE departments (
department_id bigint(20) NOT NULL AUTO_INCREMENT,
department_name varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
department_full_name varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
department_path varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
department_parent_id bigint(20) NULL,
status int(1) NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (department_id) ,
INDEX department_idx (department_id ASC, department_parent_id ASC) USING BTREE
)
COMMENT = 'Bảng danh sách các phòng ban';

CREATE TABLE positions (
postion_id bigint NOT NULL AUTO_INCREMENT,
postion_name varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
status int(1) NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (postion_id) ,
INDEX postion_idx (postion_id ASC) USING BTREE
)
COMMENT = 'Bảng danh sách các chức danh';

CREATE TABLE department_position (
department_position_id bigint(20) NOT NULL AUTO_INCREMENT,
department_id bigint(20) NULL,
position_id bigint(20) NULL,
status int(1) NULL,
created_date timestamp NULL ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (department_position_id) ,
INDEX department_position_idx (department_id ASC, position_id ASC) USING BTREE
)
COMMENT = 'Bảng danh sách các chức danh theo phòng ban';

CREATE TABLE certificates (
certificate_id bigint(20) NOT NULL AUTO_INCREMENT,
certificate_name varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
certificate_type varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Loại văn bằng chứng chỉ',
status int(1) NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (certificate_id) ,
INDEX certificate_idx (certificate_id ASC, certificate_type ASC) USING BTREE
)
COMMENT = 'Bảng dánh sách các bằng cấp, chứng chỉ';

CREATE TABLE officer_certificate (
officer_certificate_id bigint(20) NOT NULL AUTO_INCREMENT,
officer_id bigint(20) NULL,
certificate_id bigint(20) NULL,
certificate_number varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Số văn bằng chứng chỉ',
certificate_issue_date timestamp NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Ngày cấp',
certificate_issue_place varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Nơi cấp văn bằng chứng chỉ',
file_path varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Đường dẫn file',
status int(1) NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (officer_certificate_id) ,
INDEX officer_certificate_idx (officer_id ASC, certificate_id ASC) USING BTREE
)
COMMENT = 'Bảng danh sách các văn bằng chứng chỉ đã có của từng cán bộ';

CREATE TABLE position_certificate (
position_certificate_id bigint(20) NOT NULL AUTO_INCREMENT,
position_id bigint(20) NULL,
certificate_id bigint(20) NULL,
status int(1) NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (position_certificate_id) ,
INDEX position_certificate_idx (position_id ASC, certificate_id ASC) USING BTREE
)
COMMENT = 'Bảng danh sách các văn bằng chứng chỉ cần học theo chức danh';

CREATE TABLE courses (
course_id bigint(20) NOT NULL AUTO_INCREMENT,
course_name varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
lecturer_id bigint NULL,
start_date date NULL COMMENT 'Ngày khai giảng',
end_date date NULL COMMENT 'Ngày kết thúc khóa học',
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (course_id) ,
INDEX course_idx (course_id ASC, lecturer_id ASC, start_date ASC, end_date ASC) USING BTREE
)
COMMENT = 'Bảng các khóa học';

CREATE TABLE evaluations (
evaluation_id bigint(20) NOT NULL AUTO_INCREMENT,
course_id bigint(20) NULL,
course_point int(1) NULL COMMENT 'Điểm đánh giá cho khóa học',
course_comment varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Nhận xét về khóa học',
lecturer_point int(1) NULL COMMENT 'Điểm đánh giá cho giảng viên',
lecturer_comment varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Nhận xét về giảng viên',
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (evaluation_id) ,
INDEX evaluation_idx (evaluation_id ASC, course_id ASC) USING BTREE
)
COMMENT = 'Bảng đánh giá khóa học';

CREATE TABLE experiences (
experience_id bigint(20) NOT NULL AUTO_INCREMENT,
lecturer_id bigint(20) NULL,
teach_plan double NULL COMMENT 'Số giờ dạy theo kế hoạch',
teach_effect double NULL COMMENT 'Số giờ dạy thực tế',
teach_year int(10) NULL COMMENT 'Năm giảng dạy',
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (experience_id) ,
INDEX experience_idx (experience_id ASC, lecturer_id ASC, teach_year ASC) USING BTREE
)
COMMENT = 'Bảng kinh nghiệm giảng dạy của giảng viên';

CREATE TABLE surveys (
survey_id bigint(20) NOT NULL AUTO_INCREMENT,
survey_title varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
survey_description varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
start_time date NULL,
end_time date NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (survey_id) ,
INDEX survey_idx (survey_id ASC, start_time ASC, end_time ASC) USING BTREE
)
COMMENT = 'Bảng dánh sách các khảo sát';

CREATE TABLE questions (
question_id bigint(20) NOT NULL AUTO_INCREMENT,
survey_id bigint(20) NULL,
question_content varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Nội dung câu hỏi',
question_type int(1) NULL COMMENT 'Loại câu hỏi: 1 lựa chọn, nhiều lựa chọn, mở,....',
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (question_id) ,
INDEX question_idx (question_id ASC, survey_id ASC) USING BTREE
)
COMMENT = 'Bảng câu hỏi khảo sát';

CREATE TABLE survey_results (
survey_result_id bigint(20) NOT NULL AUTO_INCREMENT,
survey_id bigint(20) NULL,
question_id bigint(20) NULL,
officer_id bigint(20) NULL,
answer varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (survey_result_id) ,
INDEX survey_result_idx (survey_id ASC, question_id ASC, officer_id ASC) USING BTREE
)
COMMENT = 'Bảng chứa kết quả khảo sát';

CREATE TABLE answers (
answer_id bigint(20) NOT NULL AUTO_INCREMENT,
question_id bigint(20) NULL,
answer_name varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (answer_id) ,
INDEX answer_idx (answer_id ASC, question_id ASC) USING BTREE
)
COMMENT = 'Bảng danh sách đáp án của câu hỏi khảo sát';

CREATE TABLE officer_course (
officer_course_id bigint(20) NOT NULL AUTO_INCREMENT,
officer_id bigint(20) NULL,
course_id bigint(20) NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (officer_course_id) ,
INDEX officer_course_idx (officer_id ASC, course_id ASC) USING BTREE
)
COMMENT = 'Bảng map học viên với khóa học';

CREATE TABLE feedbacks (
feedback_id bigint(20) NOT NULL AUTO_INCREMENT,
course_id bigint(20) NULL,
officer_id bigint(20) NULL,
feedback_content varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (feedback_id) ,
INDEX feedback_idx (feedback_id ASC, course_id ASC, officer_id ASC) USING BTREE
)
COMMENT = 'Bảng chứa thông tin phản hồi của học viên về khóa học';

/*
ALTER TABLE answers ADD CONSTRAINT fk_answers_answers_1 FOREIGN KEY (question_id) REFERENCES questions (question_id);
ALTER TABLE questions ADD CONSTRAINT fk_questions_questions_1 FOREIGN KEY (survey_id) REFERENCES surveys (survey_id);
ALTER TABLE survey_results ADD CONSTRAINT fk_survey_results_survey_results_1 FOREIGN KEY (survey_id) REFERENCES surveys (survey_id);
ALTER TABLE survey_results ADD CONSTRAINT fk_survey_results_survey_results_2 FOREIGN KEY (officer_id) REFERENCES officers (officer_id);
ALTER TABLE officers ADD CONSTRAINT fk_officers_officers_1 FOREIGN KEY (department_id) REFERENCES departments (department_id);
ALTER TABLE officers ADD CONSTRAINT fk_officers_officers_2 FOREIGN KEY (position_id) REFERENCES positions ();
ALTER TABLE lecturers ADD CONSTRAINT fk_lecturers_lecturers_1 FOREIGN KEY (department_id) REFERENCES departments (department_id);
ALTER TABLE lecturers ADD CONSTRAINT fk_lecturers_lecturers_2 FOREIGN KEY (position_id) REFERENCES positions ();
ALTER TABLE position_certificate ADD CONSTRAINT fk_position_certificate_position_certificate_1 FOREIGN KEY (position_id) REFERENCES positions ();
ALTER TABLE position_certificate ADD CONSTRAINT fk_position_certificate_position_certificate_2 FOREIGN KEY (certificate_id) REFERENCES certificates (certificate_id);
ALTER TABLE officer_certificate ADD CONSTRAINT fk_officer_certificate_officer_certificate_1 FOREIGN KEY (certificate_id) REFERENCES officers ();
ALTER TABLE officer_certificate ADD CONSTRAINT fk_officer_certificate_officer_certificate_2 FOREIGN KEY (certificate_id) REFERENCES certificates (certificate_id);
ALTER TABLE experiences ADD CONSTRAINT fk_experiences_experiences_1 FOREIGN KEY (lecturer_id) REFERENCES lecturers (lecturer_id);
ALTER TABLE courses ADD CONSTRAINT fk_courses_courses_1 FOREIGN KEY (lecturer_id) REFERENCES lecturers (lecturer_id);
ALTER TABLE evaluations ADD CONSTRAINT fk_evaluations_evaluations_1 FOREIGN KEY (course_id) REFERENCES courses (course_id);
*/
