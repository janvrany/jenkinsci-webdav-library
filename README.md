# Jenkins WebDAV Library

This [Jenkins shared library][1] contains a [WebDAV][2] client to access and
upload files to a WebDAV-enabled site. This can be used - for example - to
upload build artifacts.

## Configuration

See [Jenkins User Handbook, chapter Extending with Shared Libraries][1] on how to configure pipeline libraries.

## Usage

In a pipeline script:

    library('webdav-library')
    node ("...") {
      ws {

       sh """
       echo "Hello World!" > test.txt
       """

       def uploads = webdav url: 'https://some.server.com/download', credentialsId: 'some_server_credentials'

       uploads.put('nightly', '*.txt')
      }
    }

## License

This library is MIT licensed. See `license.txt` for details.

[1]: https://jenkins.io/doc/book/pipeline/shared-libraries/
[2]: https://en.wikipedia.org/wiki/WebDAV
