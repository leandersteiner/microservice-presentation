FROM node:18

RUN mkdir -p /home/service
WORKDIR /home/service

COPY ./main.js .
COPY ./package.json .

CMD [ "node", "main.js" ]
