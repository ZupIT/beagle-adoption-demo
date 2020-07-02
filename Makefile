REGISTRY = $(DOCKER_REGISTRY)
RELEASE = $(RELEASE_VERSION)

BINARY_NAME=products-hack-day-backend

# Commons
HOST=127.0.0.1

all: test build
build:
	docker build -t "${REGISTRY}/${BINARY_NAME}:${RELEASE}" .

publish:
	docker push "${REGISTRY}/${BINARY_NAME}:${RELEASE}"

test:
	@echo "Tests are not running right now."
