#!/bin/bash
notify-send "Taking screenshot" -i applets-screenshooter

sleep 1

kill `pidof notify-osd`

echo "PWD = `pwd`"

result=`java screenShot`

result=`echo $result | tr -d '\r\n'` # strip newlines
echo ${result} # this might be helpful
pwd=`pwd`
if [ "${result}" = "ok" ]; then
	command=`notify-send "Uploading to imgur" -i '${pwd}/icon/loading.gif'`

	id=`java -classpath lib/commons-codec-1.7.jar:lib/json-simple-1.1.1.jar:. uploadImage "tempScreen.png" id`

	id=`echo ${id} | tr -d '\r\n'`

	kill `pidof notify-osd`

	if [ "${id}" = "-1" ]; then
		notify-send "Failed somewhere" -i "`pwd`/icon/fail.png"
	else
		link="http://imgur.com/${id}" # no https? disappointed
		notify-send "Done" "Link is: ${link} "
		nohup echo ${link} | xclip -sel clip &
	fi
else
	echo "Fail :("
	notify-send "Failed somewhere" -i "`pwd`/icon/fail.png"
fi
