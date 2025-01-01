.DEFAULT_GOAL := build-run

clean:
	make clean

build:
	make clean build

install:
	make install

run-dist:
	make run-dist

run:
	make run

test:
	make test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

build-run: build run

.PHONY: build
