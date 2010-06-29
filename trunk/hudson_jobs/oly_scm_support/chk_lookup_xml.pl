#!/usr/bin/perl
#-------------------------------------------------------------
#  $Id: chk_bpel_xsl.pl,v 1.1 2004/03/03 06:58:57 ogawa Exp $
#
#  Description: Check BPEL XSL Mapper Exists or not
#  Programmers: Hidetaka OGAWA <h-ogawa@ps.jp.nec.com>
#-------------------------------------------------------------

use File::Basename;


my $PATH_SEPARATOR = "/" ;
my @URLs;



if ( 0 <= $#ARGV )
{
  my $dir_datamodel = (1 <= $#ARGV)?$ARGV[1]:"$ARGV[0]${PATH_SEPARATOR}datamodel";

  if ( ! -e "$dir_datamodel${PATH_SEPARATOR}soa${PATH_SEPARATOR}InterfaceComponents" )
  {
    die "file not found: \'$dir_datamodel${PATH_SEPARATOR}soa${PATH_SEPARATOR}InterfaceComponents\'";
  }

  expand_dir( $ARGV[0] ) ;

  undef %saw;
  foreach my $url ( sort grep(!$saw{$_}++, @URLs) )
  {
    if ( !($url =~ /(\/soa\/InterfaceComponents\/.*?)$/) )
    {
      print "  Unknown ($url)\n";
    }
    elsif ( -e "$dir_datamodel${PATH_SEPARATOR}$1" )
    {
      #print "  Good    ($1)\n";
    }
    else
    {
      print "  Missing ($1)\n";
    }
  }
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
    if ( $entry =~ /\.(bpel|xsl)$/ )
    {
      check_xml_lookup( "$dir$PATH_SEPARATOR$entry" ) ;
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

sub check_xml_lookup
{
  my( $filepath ) = @_ ;

  open( BPEL, "<$filepath" ) || return ;
  my @fileline = <BPEL> ;
  close( BPEL ) ;

  my @urls = map {/:lookup-xml\s*\(\s*[\'\"](.*?)[\'\"]\s*,/} grep (/:lookup-xml\s*\(\s*[\'\"].*?[\'\"]\s*,/g, @fileline);
#  my $dirname = dirname( $filepath );

  push( @URLs, @urls );
}
