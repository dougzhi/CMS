FROM nginx:1.14
COPY Shanghai /etc/localtime
COPY nginx/nginx.conf /etc/nginx/
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
RUN rm -rf /usr/share/nginx/html/*
COPY dist/   /usr/share/nginx/html/