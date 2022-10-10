class Attachment < ApplicationRecord
  belongs_to :product
  has_many :attachment_views, dependent: :destroy
  has_one_attached :file
end
