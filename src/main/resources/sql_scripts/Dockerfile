# Use MySQL base image
FROM mysql:latest

# Copy the SQL initialization script to a location in the container
COPY create-db.sql /docker-entrypoint-initdb.d/

# Set environment variables (optional)
ENV MYSQL_DATABASE=educonnect
ENV MYSQL_USER=springstudent
ENV MYSQL_PASSWORD=springstudent
ENV MYSQL_ROOT_PASSWORD=root