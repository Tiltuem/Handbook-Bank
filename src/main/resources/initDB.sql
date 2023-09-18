INSERT INTO ACCOUNT_STATUS_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('ACAC', false, 'admin', NOW(), 'admin', NOW()),
       ('ACDL', false, 'admin', NOW(), 'admin', NOW());

INSERT INTO ACCOUNT_RESTRICTION_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('LMRS', false, 'admin', NOW(), 'admin', NOW()),
       ('URRS', false, 'admin', NOW(), 'admin', NOW()),
       ('CLRS', false, 'admin', NOW(), 'admin', NOW()),
       ('FPRS', false, 'admin', NOW(), 'admin', NOW()),
       ('SDRS', false, 'admin', NOW(), 'admin', NOW());
INSERT INTO CHANGE_TYPE_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('ADDD', false, 'admin', NOW(), 'admin', NOW()),
       ('CHGD', false, 'admin', NOW(), 'admin', NOW()),
       ('DLTD', false, 'admin', NOW(), 'admin', NOW());

INSERT INTO CREATION_REASON_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('RQST', false, 'admin', NOW(), 'admin', NOW()),
       ('CIBD', false, 'admin', NOW(), 'admin', NOW()),
       ('FCBD', false, 'admin', NOW(), 'admin', NOW());

INSERT INTO EXCHANGE_PARTICIPANT_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('0', false, 'admin', NOW(), 'admin', NOW()),
       ('1', false, 'admin', NOW(), 'admin', NOW());

INSERT INTO INFO_TYPE_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('FIRR', false, 'admin', NOW(), 'admin', NOW()),
       ('SIRR', false, 'admin', NOW(), 'admin', NOW());

INSERT INTO PARTICIPANT_STATUS_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('PSAC', false, 'admin', NOW(), 'admin', NOW()),
       ('PSDL', false, 'admin', NOW(), 'admin', NOW());

INSERT INTO PARTICIPANT_TYPE_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('00', false, 'admin', NOW(), 'admin', NOW()),
       ('10', false, 'admin', NOW(), 'admin', NOW()),
       ('12', false, 'admin', NOW(), 'admin', NOW()),
       ('15', false, 'admin', NOW(), 'admin', NOW()),
       ('16', false, 'admin', NOW(), 'admin', NOW()),
       ('20', false, 'admin', NOW(), 'admin', NOW()),
       ('30', false, 'admin', NOW(), 'admin', NOW()),
       ('40', false, 'admin', NOW(), 'admin', NOW()),
       ('51', false, 'admin', NOW(), 'admin', NOW()),
       ('52', false, 'admin', NOW(), 'admin', NOW()),
       ('60', false, 'admin', NOW(), 'admin', NOW()),
       ('65', false, 'admin', NOW(), 'admin', NOW()),
       ('71', false, 'admin', NOW(), 'admin', NOW()),
       ('75', false, 'admin', NOW(), 'admin', NOW()),
       ('78', false, 'admin', NOW(), 'admin', NOW()),
       ('90', false, 'admin', NOW(), 'admin', NOW()),
       ('99', false, 'admin', NOW(), 'admin', NOW());

INSERT INTO REGULATION_ACCOUNT_TYPE_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('CBRA', false, 'admin', NOW(), 'admin', NOW()),
       ('CRSA', false, 'admin', NOW(), 'admin', NOW()),
       ('BANA', false, 'admin', NOW(), 'admin', NOW()),
       ('TRSA', false, 'admin', NOW(), 'admin', NOW()),
       ('TRUA', false, 'admin', NOW(), 'admin', NOW()),
       ('CLAC', false, 'admin', NOW(), 'admin', NOW()),
       ('UTRA', false, 'admin', NOW(), 'admin', NOW());

INSERT INTO RESTRICTION_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('URRS', false, 'admin', NOW(), 'admin', NOW()),
       ('LWRS', false, 'admin', NOW(), 'admin', NOW()),
       ('MRTR', false, 'admin', NOW(), 'admin', NOW()),
       ('RSIP', false, 'admin', NOW(), 'admin', NOW()),
       ('FPIP', false, 'admin', NOW(), 'admin', NOW());

INSERT INTO SERVICE_CS_CODES(CODE, DELETED, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)
VALUES ('1', false, 'admin', NOW(), 'admin', NOW()),
       ('2', false, 'admin', NOW(), 'admin', NOW()),
       ('3', false, 'admin', NOW(), 'admin', NOW()),
       ('4', false, 'admin', NOW(), 'admin', NOW()),
       ('5', false, 'admin', NOW(), 'admin', NOW()),
       ('6', false, 'admin', NOW(), 'admin', NOW());


insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);