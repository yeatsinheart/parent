#mvn versions:use-latest-releases -DprocessAllModules=true
mvn versions:use-latest-releases
cd gen
mvn versions:use-latest-releases

cd ..
cd maven
mvn versions:use-latest-releases

cd ..
cd business
cd router
mvn versions:use-latest-releases
cd ../gate
mvn versions:use-latest-releases

cd ../..
cd component
cd mysql
mvn versions:use-latest-releases

cd ..
cd redis
mvn versions:use-latest-releases

cd ..
cd webflux
mvn versions:use-latest-releases

cd ..
cd dubbo
mvn versions:use-latest-releases

cd ..
cd common
mvn versions:use-latest-releases

cd ..
cd nacos
mvn versions:use-latest-releases
