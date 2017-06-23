#!/bin/sh
### BEGIN INIT INFO
# Provides:          cdr-forwarder
# Required-Start:    asterisk
# Required-Stop:     asterisk
# Should-Start:
# Should-Stop:
# Default-Start:     2 3
# Default-Stop:      0 6
# Short-Description: Forward CDR from mysql table to Telegram
### END INIT INFO

# Process name ( For display )
SERVICE_NAME=cdr-forwarder
#Service folder
SERVICE_FOLDER=/usr/local/MyProject/cdr-forward
# Daemon name, where is the actual executable
PATH_TO_JAR=$SERVICE_FOLDER/cdr-forwarder.jar
# pid file for the daemon
PIDFILE=/var/run/cdr_forwarder.pid

# Using the lsb functions to perform the operations.
. /lib/lsb/init-functions

# If the daemon is not there, then exit.
test -e $PATH_TO_JAR || exit 5

case $1 in
 start)
  # Checked the PID file exists and check the actual status of process
  if [ -e $PIDFILE ]; then
   status_of_proc -p $PIDFILE $PATH_TO_JAR "$SERVICE_NAME process" && status="0" || status="$?"
   # If the status is SUCCESS then don't need to start again.
   if [ $status = "0" ]; then
    exit # Exit
   fi
  fi
  # Start the daemon.
  log_daemon_msg "Starting the process" "$SERVICE_NAME"
  # Start the daemon with the help of start-stop-daemon
  # Log the message appropriately
  if start-stop-daemon --start --quiet --oknodo --pidfile $PIDFILE --chdir $SERVICE_FOLDER --exec /usr/bin/java -- -jar $PATH_TO_JAR ; then
   log_end_msg 0
  else
   log_end_msg 1
  fi
  ;;
 stop)
  # Stop the daemon.
  if [ -e $PIDFILE ]; then
   status_of_proc -p $PIDFILE $PATH_TO_JAR "Stoppping the $SERVICE_NAME process" && status="0" || status="$?"
   if [ "$status" = 0 ]; then
    start-stop-daemon --stop --quiet --oknodo --pidfile $PIDFILE
    /bin/rm -rf $PIDFILE
   fi
  else
   log_daemon_msg "$SERVICE_NAME process is not running"
   log_end_msg 0
  fi
  ;;
 restart)
  # Restart the daemon.
  $0 stop && sleep 2 && $0 start
  ;;
 status)
  # Check the status of the process.
  if [ -e $PIDFILE ]; then
   status_of_proc -p $PIDFILE $PATH_TO_JAR "$SERVICE_NAME process" && exit 0 || exit $?
  else
   log_daemon_msg "$SERVICE_NAME Process is not running"
   log_end_msg 0
  fi
  ;;
 reload)
  # Reload the process. Basically sending some signal to a daemon to reload
  # it configurations.
  if [ -e $PIDFILE ]; then
   start-stop-daemon --stop --signal USR1 --quiet --pidfile $PIDFILE --name $SERVICE_NAME
   log_success_msg "$SERVICE_NAME process reloaded successfully"
  else
   log_failure_msg "$PIDFILE does not exists"
  fi
  ;;
 *)
  # For invalid arguments, print the usage message.
  echo "Usage: $0 {start|stop|restart|reload|status}"
  exit 2
  ;;
esac