FROM openjdk:11
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_pashech_community_bot
ENV BOT_TOKEN=5592802689:AAFhaGLvjUI8_XWp_TibbJ4uxDfBnmw_A80
ENV BOT_DB_USERNAME=jrtb_db_user
ENV BOT_DB_PASSWORD=jrtb_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "/app.jar"]