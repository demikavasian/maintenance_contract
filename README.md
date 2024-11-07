# Template For Web Services 

This is a template project which can be used as starting point for a Spring Boot Webservice.

# Usage

* Create a new project on Github and choose this one as template.
* Clone the new repository locally.
* ***Do NOT open with IntelliJ yet***
* execute the shell script `bin/init.sh <my-service>`
* alternatively replace TEMPLATE_PROJECT manually everywhere
* open the project in IntelliJ
* Remove the init script
* Review the ``build.gradle`` and think about the dependencies your project requires.
* Rename the ``template`` package and ``TemplateApplication`` to a suitable name for your project.
* Replace the content of this ``README.md`` with something meaningful for your project.
* Examine ``application.yml``, ``FeignConfig`` and start implementing your service, e.g. by
  taking ``SampleController`` as a starting point.


## application-local.yml

If you want to run your app locally, create an ``resources/application-local.yml``
to tweak your config. This file **must not be checked into VCS**, since it typically would be
different for every developer.
Run the app with 'local' profile active. 

Settings you most likely want to keep there include:

```yaml
seshat.url: https://web.myplant.io/beta
server.port: 7077

# datasource config for local DB ...
# local cache 
```