pipeline {
    agent any

    parameters{
        string(name: 'deploy_prod', defaultValue: 'deploy_tomcat_prod', description: 'lslsls')
        string(name: 'deploy_stage', defaultValue: 'deploy_tomcat_stage', description: 'lslsls')
    }
    triggers {
        pollSCM('* * * * *')
    }

    stages{
        stage('Build'){
            steps {
                bat 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }

        stage ('Deployments'){
            parallel{
                stage ('deploy to staging'){
                    steps {
                        build job: 'deploy-to-staging'
                    }
                }

                stage ('Deploy to production'){
                    steps{
                        timeout(time:5, unit:'DAYS'){
                            input message:'Approve PRODUCTION Deployment?'
                        }
                        build job: 'deploy-to-prod'
                    }

                    post {
                        success {
                            echo 'Code deployed to Production.'
                        }

                        failure {
                            echo ' Deployment failed.'
                        }
                    }
                }
            }
        }
    }
}