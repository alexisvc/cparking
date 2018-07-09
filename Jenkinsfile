pipeline {
    //where and how to execute the Pipeline
    agent {
        label 'Slave_Induccion'
    }
    //agent none
    //agent any
    /*agent {
        node {
          label 'labelName'
        }
      }*/

    //Using environment variables
    environment {
        CC = 'clang'
    }

    //configuring Pipeline-specific options from within the Pipeline itself
    options {
        buildDiscarder(logRotator(numToKeepStr: '3')) //Persist artifacts and console output for the specific number of recent Pipeline runs.
        disableConcurrentBuilds() //Disallow concurrent executions of the Pipeline
        timestamps() //Prepend all console output generated by the Pipeline run with the time at which the line was emitted
    }

    //A section defining tools to auto-install and put on the PATH
    tools {
        jdk 'JDK8_Centos'
        gradle 'Gradle4.5_Centos'
    }

    triggers {
        pollSCM('@hourly')
        //cron('H */4 * * 1-5')
    }

	stages{
        stage('Print Enviroment') {
            steps{
                echo "------------>Printing enviroment<------------"
                //execute a shell command in a Pipeline
                sh 'gradle --version'
                sh 'java -version'
                //execute a shell multiline command in a Pipeline
                sh '''
                    ls -lah
                '''
            }
        }

        stage('Checkout'){
            steps{
                echo "------------>Checkout<------------"
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], 
                gitTool: 'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:'GitHub_jonathanavalencia', 
                url:'https://github.com/jonathanavalencia/cparking.git']]])
            }
        }

        stage('Compile'){
            steps{
                echo "------------>Compile<------------"
                sh 'gradle --b ./build.gradle compileJava'
            }
        }

        stage('Unit tests') {
            steps{
                echo "------------>Unit tests<------------"
                sh 'gradle test'
                junit '**/build/test-results/test/*.xml' //aggregate test results - JUnit
                jacoco classPattern: '**/build/classes/java', execPattern: '**/build/jacoco/test.exec', sourcePattern: '**/src/main/java'
            }
        }

        stage('Static code analysis') {
            steps{
                echo '------------>Static code analysis<------------'
                withSonarQubeEnv('Sonar') {
                    sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
                }
            }
        }

        stage('Build') {
            steps {
                echo "------------>Build<------------"
                sh 'gradle --b ./build.gradle build -x test'
            }
        }

        /*  
    	stage('Parallel Stage') {
    	    parallel {
    	        stage('Branch A') {
    	            steps {
    	                echo "------------>On Branch A<------------"
    	                sh 'echo "Este Nodo es: Centos =)"'
    	            }
    	       }
    	       stage('Branch B') {
    	           steps {
    	               echo "------------>On Branch Be<------------"
    	           }
    	       }
    	   }
    	}

        
        stage('Publish') {
            steps{
                echo '------------>Publish [Artifactory]<------------'
                script{ //takes a block of Scripted Pipeline and executes that in the Declarative Pipeline
                    def server = Artifactory.server 'ar7if4c70ry@c318a'
                    def uploadSpec = '''
                        {"files": [{
                        "pattern": "**-/build/libs/*.jar",
                        "target": "libs-release-local/$JOB_NAME/build/"
                        }]}'''

                    def buildInfo = server.upload(uploadSpec)
                    server.publishBuildInfo(buildInfo)
                }
            }
        }
        */

        /*
        stage('Deploy') {
            steps {
                //Asking for human input to proceed
                //input "Deploy?"
                echo "------------>Deploying<------------"
            }
        }
        */ 
	}

    post {
        always {
           echo "------------>Reporting<------------"
          publishHTML target: [
            allowMissing: true,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'buildSrc/build/reports/tests/test',
            reportFiles: 'index.html',
            reportName: 'Tests Report - buildSrc'
          ]

        publishHTML target: [
            allowMissing: true,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'services/webservice/build/reports/tests/test',
            reportFiles: 'index.html',
            reportName: 'Tests Report - services/webservice'
          ]
          
        publishHTML target: [
            allowMissing: true,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'shared/build/reports/tests/test',
            reportFiles: 'index.html',
            reportName: 'Tests Report - shared'
          ]
        }
        success {
          echo 'Reporting successful'

        }

        failure {
          echo 'Reporting failed'
//      Send notifications about a Pipeline to an email
          mail (to: 'jonathan.valencia@ceiba.com.co',
               subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
               body: "Something is wrong with ${env.BUILD_URL}")
        }
        unstable {
          echo 'The pipeline run was marked as unstable'
        }
        changed {

//      This will run only if the state of the Pipeline has changed
//      For example, if the Pipeline was previously failing but is now successful'
//      Send notifications about a Pipeline to an email
          mail (to: 'jonathan.valencia@ceiba.com.co',
               subject: "Changed State Pipeline: ${currentBuild.fullDisplayName}",
               body: "The state of the Pipeline has changed. See ${env.BUILD_URL}")
        }
    }
}
