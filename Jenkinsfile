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
                    gv.incrementalVersion()
                }
            }
        }
                    
        
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

