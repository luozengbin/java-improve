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
    if ( $entry =~ /\.bpel$/ )
    {
      check_xslt_mapper( "$dir$PATH_SEPARATOR$entry" ) ;
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

sub check_xslt_mapper
{
  my( $filepath ) = @_ ;

  open( BPEL, "<$filepath" ) || return ;
  my @fileline = <BPEL> ;
  close( BPEL ) ;

  my @xslt = map {/:processXSLT\s*?\(\s*?[\'\"](\w+\.xsl)[\'\"]\s*?,/} grep (/:processXSLT\s*?\(\s*?[\'\"]\w+\.xsl[\'\"]\s*?,/g, @fileline);
  my $dirname = dirname( $filepath );

  foreach my $xsl (@xslt)
  {
    print "Missing XSLT mapper: $dirname$PATH_SEPARATOR$xsl\n" unless ( -f "$dirname$PATH_SEPARATOR$xsl" );
  }
}
