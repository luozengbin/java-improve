require 'rubygems'
require 'active_resource'

# Issue model on the client side
class Issue < ActiveResource::Base
  self.site = 'http://localhost/'
  self.user = 'nec009'
  self.password = 'pronec009'
end

# Retrieving issues
issues = Issue.find(:all)
puts issues.first.subject