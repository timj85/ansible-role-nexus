import groovy.json.JsonSlurper
import org.sonatype.nexus.security.realm.RealmManager

parsed_args = new JsonSlurper().parseText(args)

realmManager = container.lookup(RealmManager.class.getName())

// enable/disable the LDAP Realm
realmManager.enableRealm("LdapRealm", parsed_args.ldap_realm)
