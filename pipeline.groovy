pipeline {
    agent any
    
    stages {
        stage('Checkout'){
            steps{
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/spring-projects/spring-petclinic.git'
            }
        }
        stage('Build') {
            steps {

                
                bat './mvnw clean package'

                // Run Maven on a Unix agent.
                // sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                
              always {
                // One or more steps need to be included within each condition's block.
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts 'target/*.jar'
              }
                
            } 
        }
    }
}
