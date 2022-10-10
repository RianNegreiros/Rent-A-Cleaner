class Account < ApplicationRecord
  belongs_to :user
  has_one :store, through: :user
end
