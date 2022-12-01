FROM mysql:8.0.31
# Add a database
ENV MYSQL_DATABASE ticketdb
# run .sql scripts
COPY ./sqlScript/ /docker-entrypoint-initdb.d/
