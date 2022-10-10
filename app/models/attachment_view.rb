class AttachmentView < ApplicationRecord
  belongs_to :attachment, counter_cache: :views_count
  belongs_to :customer
end
