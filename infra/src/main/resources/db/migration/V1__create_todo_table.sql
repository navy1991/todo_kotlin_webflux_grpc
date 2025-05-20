CREATE TABLE todos (
    id INTEGER NOT NULL PRIMARY KEY COMMENT 'ID',
    content VARCHAR(50) NOT NULL COMMENT '内容',
    --priority ENUM('LOW', 'MIDDLE', 'HIGH') NOT NULL COMMENT '優先度',
    priority VARCHAR(255) NOT NULL COMMENT '優先度',
    due_date DATETIME COMMENT '期限日'
) COMMENT = 'Todo情報';
