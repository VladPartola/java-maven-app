#!/usr/bin/env groovy

@Library('jenkins-shared-library')

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
        stage("build jar") {
            steps {
                script {
                   buildJar()
                }
            }
        }
        stage("build and push image") {
            steps {
                script {
                    buildImage 'vladpartola/java-maven-app:jma-4.0'
                    dockerLogin()
                    dockerPush 'vladpartola/java-maven-app:jma-4.0'
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
