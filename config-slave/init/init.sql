CHANGE MASTER TO 
    MASTER_HOST='master',
    MASTER_USER='replica_user',
    MASTER_PASSWORD='password',
    MASTER_LOG_FILE='mysql-bin.000003',
    MASTER_LOG_POS=154;
START SLAVE;
SHOW SLAVE STATUS;