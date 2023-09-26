pipeline {
agent {
docker {
image 'maven:3-amazoncorretto-17'
args '-p 33333:8090'
}
}
environment {
HOME = '.'
}
stages {
stage('Source') {
steps {
git branch: 'main',
url: 'https://github.com/nachpolRN/wisdom-book.git'
}
}
stage('Build') {
steps {
sh 'mvn package -DskipTests'
}
}
stage('Test') {
steps {
echo 'testing...'
}
}
stage('Deploy') {
steps {
sh 'java -jar ./target/book-1.0.jar'
}
}
}
}
