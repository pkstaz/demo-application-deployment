# Base image https://hub.docker.com/u/rocker/
FROM docker.io/rocker/shiny:latest

# expose port
EXPOSE 3838

# system libraries of general use
## install debian packages
RUN apt-get update -qq && apt-get -y --no-install-recommends install \
    libxml2-dev \
    libcairo2-dev \
    libsqlite3-dev \
    libmariadbd-dev \
    libpq-dev \
    libssh2-1-dev \
    unixodbc-dev \
    libcurl4-openssl-dev \
    libssl-dev

## update system libraries
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get clean


# install renv & restore packages
RUN Rscript -e 'install.packages("renv")'
RUN Rscript -e 'renv::consent(provided = TRUE)'
# RUN Rscript -e 'renv::restore()'


# copy necessary files
## app folder
RUN addgroup --system app \
    && adduser --system --ingroup app app
WORKDIR /home/app
COPY app .
RUN chown app:app -R /home/app
USER app
CMD ["R", "-e", "shiny::runApp('/home/app',host = '0.0.0.0', port = 3838)"]