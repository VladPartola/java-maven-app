pipeline {
    agent any
    tools {
        maven 'maven-3.8.3'
    }
    stages {
        stage("build jar") {
            steps {
                echo "building the application"
                sh 'mvn package'
            }
            stage("build docker image") {
            steps {
                echo "building a docker image"
                withCredentials([usernamePassword(credentialsId: 'docker-key', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')])
                    sh 'docker build -t vladpartola/java-maven-app:jma-1.5 .'
                    sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                    sh 'docker push vladpartola/java-maven-app:jma-1.5'
            }
        stage("deploy") {
            steps {
                echo "deploying the application"
            }
        }
    }   
}
