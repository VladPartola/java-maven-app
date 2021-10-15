def buildJar() {
    echo "building the application..."
    sh 'mvn clean package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-key', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh """docker build -t vladpartola/java-maven-app:$IMAGE_NAME ."""
        sh """echo $PASSWORD | docker login -u $USERNAME --password-stdin"""
        sh """docker push vladpartola/java-maven-app:$IMAGE_NAME"""
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
