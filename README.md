# Ansible Role: Nexus 3 OSS

Эта роль устанавливает и настраивает Sonatype Nexus Repository Manager OSS

## Requirements

 Необходимые пакеты:
 - rsync
 - unzip
 - Java-17-openjdk-devel (обязательно)

---------------------------------------------------------
Рекомендованные системные требования:

1) CPU - 4 Core
2) RAM - 4 Gb
3) HDD - 40 Gb
4) Secondry HDD - 50Gb 
---------------------------------------------------------

## Role Variables

Возможные переменные вместе со значениями по умолчанию (см. `Default / main.yml`):

### General variables

- `nexus_version:` 3.28.1-01 - версия nexus. Смотрите актуальную https://www.sonatype.com/download-oss-sonatype.
- `nexus_download_url:`https://help.sonatype.com/en/download.html - путь откуда скачивается nexus.
- `nexus_backup_dir:` /var/nexus-backup - путь для хранения бэкапов. Функционал не реализован.
### Nexus OS user and group
- `nexus_os_group:` nexus - системная группа которой принадлежат файлы и от которой работает процесс.
- `nexus_os_user:` nexus - системный пользователь которому принадлежат файлы и от которого работает процесс.
- --
- `nexus_os_max_filedescriptors:` 65536 - максимальное количество открытых файловых дескриптеров для пользователя. 
- `nexus_installation_dir:` /opt - установочная директория
- `nexus_data_dir:` /var/nexus - содержит всю конфигурацию, репозитории и загруженные артефакты. Пользовательские пути к хранилищам больших двоичных объектов за пределами `nexus_data_dir` можно настроить `nexus_blobstores`.
- `nexus_tmp_dir:` /tmp/nexus - директория в которой содержаться временные файлы.
### Nexus port, context path ans listening IP
- `nexus_default_port:` 8081 - порт на котором работает служба.
- `nexus_listen:` "{{ ansible_default_ipv4.address }}" - ip адрес на котором работает служба.
- `nexus_default_context_path:` / - при установке должна сохраняться завершающая косая черта, например. : `nexus_default_context_path: '/ nexus /'`
- ---
### Nexus JVM Ram setting

```yaml
nexus_min_heap_size: "2703M"
nexus_max_heap_size: "{{ nexus_min_heap_size }}"
nexus_max_direct_memory: "{{ nexus_min_heap_size }}"
```
Это значения по умолчанию для Nexus. **Пожалуйста, не изменяйте эти значения**, если вы не прочитали [раздел памяти о системных требованиях nexus](https://help.sonatype.com/repomanager3/system-requirements#SystemRequirements-Memory) и не понимаете, что вы делаете.

В качестве второго китайского предупреждения вот выдержка из вышеуказанного документа:
> Не рекомендуется увеличивать размер JVM больше рекомендованных значений в попытке повысить производительность. На самом деле это может иметь противоположный эффект, заставляя операционную систему без нужды ломать голову.
- --
- `nexus_onboarding_wizard:` false - Управляет запуском мастера подключения Nexus при первом входе администратора в систему.
- `nexus_admin_password: `'admin123' - пароль администратора для входа в систему через web интерфейс.
- `nexus_plugin_urls:` [] - путь к дополнительным плагинам.
- `nexus_api_scheme:` http - протокол по которому будет работать api.
- `nexus_anonymous_access:` false - Allow [anonymous access](https://help.sonatype.com/display/NXRM3/Anonymous+Access) to nexus.

```
Подключает дополнительное хранилище артефактов. Может быть реализовано на дополнительном диске `type: file` или в S3
