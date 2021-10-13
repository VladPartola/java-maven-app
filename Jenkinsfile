CODE_CHANGES = getGitChanges()
pipeline {
    agent any
    environment {
        NEW_VERSION = '1.3.0'
        SERVER_CREDENTIALS = credentials('id of the credentials') //first type of use credential variables
    }
    stages {
        stage("build") {
            when {
                expression {
                    BRANCH_NAME == 'dev' && CODE_CHANGES == true
                }
            }
            steps {
                echo "building the application"
                echo "building version ${NEW_VERSION}"
            }
        }
        stage("test") {
            when {
                expression {
                    BRANCH_NAME == 'dev' ||  BRANCH_NAME == 'main'
                }
            }
            steps {
                echo "testing the application"
            }
        }
        stage("deploy") {
            steps {
                echo "deploying the application"
                withCredentials([
                    usernamePassword(credentials: 'id of the credentials', usernameVariable: USER, passwordVariable: PASSWORD) //second type of use credential variables
                ])
                echo "deploying with ${SERVER_CREDENTIALS}"
            }
        }
    }   
}
