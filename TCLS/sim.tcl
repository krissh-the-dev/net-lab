set ns [new Simulator]
set tracefile1 [open out.tr w]

$ns trace-all $tracefile1

set namfile [open out.nam w]

$ns namtrace-all $namfile

proc finish { } {
  global ns tracefile1 namfile
  $ns flush-trace
  close $tracefile1
  close $namfile
  exec nam out.nam & exit 0
}

$ns at 125.0 "finish"

$ns run

set n0 [$ns node]
set n2 [$ns node]
$ns duplex-link $n0 $n2 10Mb 10ms DropTail
$ns queue-limit $n0 $n2 20
set tcp [new Agent/TCP]
set sink [new Agent/TCPSink]
set udp [new Agent/UDP]

$ns attach-agent $n1 $udp set null [new Agent/Null]

$ns attach-agent $n5 $null

$ns connect $udp $null

$udp set fid_2

set cbr [new Application/Traffic/CBR]

$cbr attach-agent $udp

$cbr set packetsize 100

$cbr set rate 0.01Mb

$cbr set random false

$cbr set interval 0.005
$ns at 0.1 "$cbr start"

$ns at 1.0 "$ftp start"

$ns at 124.0 "$ftp stop"

$ns at 124.5 "$cbr stop"
