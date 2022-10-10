class Customer < ApplicationRecord
  belongs_to :store
  validates :store_id, uniqueness: { scope: :email }
end
