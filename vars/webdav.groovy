import hudson.FilePath;
import jenkins.model.Jenkins;
import com.cloudbees.plugins.credentials.CredentialsProvider;
import com.cloudbees.plugins.credentials.common.StandardUsernameCredentials;
import org.jenkins.library.webdav.*


def call(Map opts = [:]) {
    assert opts.credentialsId != null
    assert opts.url != null;

    def workspace;

    if (env['NODE_NAME'] == null) {
        error "webdav: no node in current context"
    }
    if (env['WORKSPACE'] == null) {
        error "webdav: no workspace in current context"
    }
    if (env['NODE_NAME'].equals("master")) {
        workspace = new FilePath(null, env['WORKSPACE'])
    } else {
        workspace = new FilePath(Jenkins.getInstance().getComputer(env['NODE_NAME']).getChannel(), env['WORKSPACE'])
    }
    def client = null;
    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: opts.credentialsId, passwordVariable: 'pass', usernameVariable: 'user']]) {
        client =  new WebDavClient(this, workspace, opts.url, user, pass);
    }
    return client;
};

return this;