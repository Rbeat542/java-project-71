run-dist:
	./build/install/app/bin/app
	
all:	
	gradle clean
	gradle installDist
	./build/install/app/bin/app	
