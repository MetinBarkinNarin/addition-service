node() {

    def repoURL = 'https://github.com/necati-ozdemir/calculator-end-to-end-test.git'

    stage("Prepare Workspace") {
        cleanWs()
        env.WORKSPACE_LOCAL = sh(returnStdout: true, script: 'pwd').trim()
        env.BUILD_TIME = sh(returnStdout: true, script: 'date +%F-%T').trim()
        echo "Workspace set to:" + env.WORKSPACE_LOCAL
        echo "Build time:" + env.BUILD_TIME
    }
    stage('Checkout Self') {
        git branch: 'master', credentialsId: '', url: repoURL
    }
    stage('Docker') {

            docker.image('mbarkin26/addition-service:latest').withRun('-e "SERVER_PORT: 8070" -p 8070:8070')
            docker.image('mbarkin26/subtraction-service:latest').withRun('-e "SERVER_PORT: 8071" -p 8071:8071')
	    docker.image('mbarkin26/calculator-service:latest').withRun('-e "SERVER_PORT: 8072" -p 8072:8072')
            
  

    }
    stage('Cucumber Tests') {
        withMaven(maven: 'maven-3') {
            sh """
			cd ${env.WORKSPACE_LOCAL}
			mvn clean test
		"""
        }
    }
    stage('Expose report') {
        archive '**/cucumber.json'
        cucumber '**/cucumber.json'
    }
/*	stage('Import results to Xray') {

		def description = "[BUILD_URL|${env.BUILD_URL}]"
		def labels = '["regression","automated_regression"]'
		def environment = "DEV"
		def testExecutionFieldId = 10007
		def testEnvironmentFieldName = "customfield_10131"
		def projectKey = "WOO"
		def xrayConnectorId = "ff3e7e37-2693-4260-a2b6-7cfacfcf9527"
		def info = '''{
				"fields": {
					"project": {
					"key": "''' + projectKey + '''"
				},
				"labels":''' + labels + ''',
				"description":"''' + description + '''",
				"summary": "Automated Regression Execution @ ''' + env.BUILD_TIME + ' ' + environment + ''' " ,
				"issuetype": {
				"id": "''' + testExecutionFieldId + '''"
				},
				"''' + testEnvironmentFieldName + '''" : [
				"''' + environment + '''"
				]
				}
				}'''
			echo info
			step([$class: 'XrayImportBuilder', endpointName: 'CUCUMBER_MULTIPART', importFilePath: 'target/cucumber.json', importInfo: info, inputInfoSwitcher: 'fileContent', serverInstance: xrayConnectorId ])
		}
		*/
}
