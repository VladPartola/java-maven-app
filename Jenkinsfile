pipeline {
    agent any
    tools {
        maven 'maven-3.8.3'
    }
    
    stages {
        stage("increment version") {
            steps {
                script {
                  echo 'incrementing app version...'
                  sh "mvn build-helper:parse-version versions:set \
                      -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                       versions:commit"
                  def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                  def version = matcher[0][1]
                  env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
                    
        
        stage("build jar") {
            steps {
                script {
                   echo "building the application..."
                   sh 'mvn clean package'
                }
            }
        }
        stage("build docker image") {
            steps {
                script {
                    echo 'building the docker image...'
                    echo "${IMAGE_NAME}"
                    sh('docker build -t vladpartola/java-maven-app:${IMAGE_NAME} .')
                }
            }
        }
        stage("deploy docker image to dockerhub") {
            steps {
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-key', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                         sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                         sh('docker push vladpartola/java-maven-app:${IMAGE_NAME}')  
                    }
                }
            }
        }
    }      
}   

