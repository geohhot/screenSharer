#!/usr/bin/perl

# take screen shot
`notify-send "Taking screenshot" -i applets-screenshooter`;

`sleep 1`;

$pid = `pidof notify-osd`;
`kill $pid`;

#$PWD = `pwd`;

print "PWD = $PWD";

`cd $PWD`;

$ok = `java screenShot`;
chop $ok;

$pid = `pidof notify-osd`;
`kill $pid`;

#print "OK = $ok\n";

if ( $ok eq "ok" ) {
	# show notification about uploading
	$command = 'notify-send "Uploading to imgur" -i '.$PWD.'/icon/loading.gif';
	`$command`;

	# upload picture
	$id = `java -classpath lib/commons-codec-1.7.jar:lib/json-simple-1.1.1.jar:. uploadImage "tempScreen.png" id`;

	# print "$id\n";
	chop $id;

	$pid = `pidof notify-osd`;
	`kill $pid`;

	if ($id eq "-1") {
		`notify-send "Failed somewhere" -i $/icon/fail.png`;
	} else {
		$link = "http://imgur.com/".$id;
		#print "\n";
		`notify-send "Done" "Link is: ".$link." "`;

		# set link to clipboard
		`nohup echo "$link" | xclip -sel clip &`;
	}

	# remove temp file
}
else {
	# show notification about fail
	print "Fail :(\n";
	`notify-send "Failed somewhere" -i $pwd/icon/fail.png`;
}