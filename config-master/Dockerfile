FROM mysql:8.0.0

ARG server_id

ENV MYSQL_ROOT_PASSWORD="ROOT"
ENV MYSQL_USER="RIOSB"
ENV MYSQL_PASSWORD="TEST"
ENV MYSQL_DATABASE="CAR_RENTAL"
ENV MYSQL_ROOT_HOST="%"

RUN touch /etc/mysql/my.cnf
RUN echo "[mysqld]" >> /etc/mysql/my.cnf
RUN echo "server_id=$server_id" >> /etc/mysql/my.cnf
RUN echo "log_bin=mysql-bin" >> /etc/mysql/my.cnf
RUN echo "binlog_format=mixed" >> /etc/mysql/my.cnf
RUN echo "bind-address=0.0.0.0" >> /etc/mysql/my.cnf
RUN echo "skip_ssl=false" >> /etc/mysql/my.cnf

RUN chmod 0444 /etc/mysql/my.cnf

EXPOSE 3306