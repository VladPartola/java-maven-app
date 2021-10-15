def script

    Docker(script) {
        this.script = script
    }
def buildJar() {
    echo "building the application..."
    sh 'mvn clean package'
} 

def buildImage() {
    echo "building the docker image..."
    script.echo "building the docker image..."
    script.withCredentials([script.usernamePassword(credentialsId: 'docker-key', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
            script.sh "echo $script.PASSWORD | docker login -u $script.USERNAME --password-stdin"
    script.sh "docker push vladpartola/java-maven-app:$IMAGE_NAME"
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
