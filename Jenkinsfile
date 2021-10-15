def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.8.3'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        
        stage("increment version") {
            steps {
                script {
                    echo 'incrementing app version...'
                    sh "mvn build-helper:pars-version versions:set \
                       -DnewVersion=\\\${parsedVersion:majorVersion}.\\\${parsedVersion:minorVersion}.\\\${parsedVersion:nextIncrementalVersion} \
                       versions:commit"
                    
        
        stage("build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("build docker image") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
    }      
}   

