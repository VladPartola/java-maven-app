def incrementalVersion() {
     echo 'incrementing app version...'
     sh "mvn build-helper:parse-version versions:set \
         -DnewVersion=\\\${parsedVersion:majorVersion}.\\\${parsedVersion:minorVersion}.\\\${parsedVersion:nextIncrementalVersion} \
         versions:commit"
     def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
     def version = matcher[0][1]
     env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}

def buildJar() {
    echo "building the application..."
    sh 'mvn clean package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-key', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh "docker build -t vladpartola/java-maven-app:test_$IMAGE_NAME ."
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh 'docker push vladpartola/java-maven-app:$IMAGE_NAME'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
