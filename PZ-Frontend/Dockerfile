FROM node:latest as build
ARG build_env=development

WORKDIR /app

RUN npm cache clean --force

COPY . .

RUN npm install

RUN ./node_modules/@angular/cli/bin/ng build --configuration=$build_env

# Serve Application using Nginx Server

FROM nginx:1.20 as ngx

COPY --from=build /app/dist/pz-frontend /usr/share/nginx/html

#copying nginx config from local to image
COPY /nginx.conf /etc/nginx/conf.d/default.conf

#exposing internal port
EXPOSE 80
