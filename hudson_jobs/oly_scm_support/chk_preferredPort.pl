#!/usr/bin/perl
#-------------------------------------------------------------
#  $Id: chk_bpel_xsl.pl,v 1.1 2004/03/03 06:58:57 ogawa Exp $
#
#  Description: Check BPEL XSL Mapper Exists or not
#  Programmers: Hidetaka OGAWA <h-ogawa@ps.jp.nec.com>
#-------------------------------------------------------------

use File::Basename;


$PATH_SEPARATOR = "/" ;


if ( 0 <= $#ARGV )
{
  expand_dir( $ARGV[0] ) ;
}
else
{
  print "  Usage: $0 \'directory_tree\' (path separator must be \'/\')\n\n" ;
}

1;

#
# subroutines below
#

sub expand_dir
{
  my( $dir ) = @_ ;

  opendir( DIR_HANDLE, $dir ) || return ;
  my( @entry ) = grep { !/^\.+$/ } readdir( DIR_HANDLE ) ;
  closedir( DIR_HANDLE ) ;

  foreach my $entry ( @entry )
  {
    #-------------------------
    #  make recursive call
    #-------------------------
    if ( $entry =~ /bpel.xml$/ )
    {
      print "$dir$PATH_SEPARATOR$entry\n"
    }
    elsif ( -d "$dir$PATH_SEPARATOR$entry" )
    {
      expand_dir( "$dir$PATH_SEPARATOR$entry" ) ;
    }
    else
    {
      ;  # nothing to do
    }
  }
}
