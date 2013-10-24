#!/usr/bin/perl



 use English;

 use strict;

  
 my @chars=('a'..'z','A'..'Z','0'..'9','_',' ');

  my ($lines) = $ARGV[0];

   

   foreach (1..$lines) {

     print randomString(int(rand(120 - 80)) + 80) . "\n";

     }
     exit;

      

      sub randomString() {

        my $length = shift;

          my $randomString;

            foreach (1..$length) {

                 $randomString = $randomString . $chars[int(rand @chars)];

                   }

                     return $randomString;

                     }
