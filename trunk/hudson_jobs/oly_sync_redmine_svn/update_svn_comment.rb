#! ruby -akira
require "kconv"
print "�R�����g�ύX���m���� - Start\n"
Repository.find(:all).each {|repository| 
revisions = repository.scm.revisions(nil)
length = 100
if length > revisions.length
	length = revisions.length
end
length = length -1
for i in 0..length
	revisions_top = revisions[i]
	rev = revisions_top.identifier
	changeset = repository.changesets.find_by_revision(rev)
	if revisions_top.message.strip != changeset.comments.strip
		print "���r�W����#{rev}�̃R�����g���ύX���ꂽ���߁Aredmine�֓�������...\n"
		changeset.update_attribute("comments", revisions_top.message)
		changeset.scan_comment_for_issue_ids
	end
end
}
print "�R�����g�ύX���m���� - End\n"