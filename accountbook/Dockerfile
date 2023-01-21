FROM gradle:7.4-jdk17-alpine as builder
WORKDIR /build

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함.
COPY build.gradle /build/
COPY settings.gradle /build/

RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

#COPY gradlew /build/
#COPY gradle /build/
#COPY src /build/

# 빌더 이미지에서 애플리케이션 빌드
COPY . /build
RUN gradle build -x test --parallel

# Application
FROM openjdk:17.0-slim
WORKDIR /app

# 빌더 이미지에서 jar 파일만 복사
COPY --from=builder /build/build/libs/*.jar abapp.jar

#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} abapp.jar

ENTRYPOINT [ "java", "-jar", "abapp.jar" ]